pipeline {
  agent any

  environment {
    REGISTRY   = 'tiagotlg'
    IMAGE_NAME = 'pratica_ac2'
    IMAGE_TAG  = "build-${env.BUILD_NUMBER}"
    FULL_IMAGE = "${REGISTRY}/${IMAGE_NAME}:${IMAGE_TAG}"
  }

  stages {
    stage('Pre-Build - Checkout') {
      steps {
        checkout scm
      }
    }

    stage('Pre-Build - Build & Test (Maven)') {
      steps {
        bat 'mvn clean verify -DskipTests=false'
      }
    }


    stage('Build - Docker Image') {
      steps {
        bat "docker build -t ${FULL_IMAGE} ."
      }
    }

    stage('Build - Trivy Scan - Filesystem') {
      steps {
        bat 'trivy fs --exit-code 1 --severity HIGH,CRITICAL --format table --output trivy-fs-report.txt .'
        archiveArtifacts artifacts: 'trivy-fs-report.txt', onlyIfSuccessful: false
      }
    }

    stage('Build - Trivy Scan - Image') {
      steps {
        bat "trivy image --exit-code 1 --severity HIGH,CRITICAL --format table --output trivy-image-report.txt ${FULL_IMAGE}"
        archiveArtifacts artifacts: 'trivy-image-report.txt', onlyIfSuccessful: false
      }
    }


    stage('Post-Build - Docker Login & Push') {
      steps {
        withCredentials([
          usernamePassword(
            credentialsId: 'DOCKERHUB_CRED',
            usernameVariable: 'DOCKER_USER',
            passwordVariable: 'DOCKER_PASS'
          )
        ]) {
          bat 'docker login -u %DOCKER_USER% -p %DOCKER_PASS%'
          bat "docker push ${FULL_IMAGE}"
          bat "docker tag ${FULL_IMAGE} ${REGISTRY}/${IMAGE_NAME}:latest"
          bat "docker push ${REGISTRY}/${IMAGE_NAME}:latest"
        }
      }
    }
  }

  post {
    always {
      echo 'Pipeline CI finalizado com DevSecOps (Trivy).'
    }
  }
}
