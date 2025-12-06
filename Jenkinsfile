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


    stage('Build - Docker Image (simulado)') {
      steps {
        echo 'Docker não disponível neste ambiente; etapa de build da imagem simulada apenas para fins de demonstração.'
      }
    }

    stage('Build - Trivy Scan - Filesystem') {
      steps {
        bat 'trivy fs --exit-code 0 --severity HIGH,CRITICAL --format table --output trivy-fs-report.txt .'
        archiveArtifacts artifacts: 'trivy-fs-report.txt', onlyIfSuccessful: false
      }
    }

    stage('Build - Trivy Scan - Image (simulado)') {
      steps {
        echo 'Scan de imagem Docker com Trivy não executado porque a imagem não é construída neste ambiente; descrito conceitualmente no relatório.'
      }
    }


    stage('Post-Build - Docker Login & Push (simulado)') {
      steps {
        echo 'Login e push para Docker Hub omitidos neste ambiente (sem daemon Docker); etapa simulada para fins acadêmicos.'
      }
    }
  }

  post {
    always {
      echo 'Pipeline CI em modo demonstrativo (com Trivy filesystem) finalizado.'
    }
  }
}
