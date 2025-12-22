pipeline {
  agent any

  environment {
    COMPOSE_PROJECT_NAME = "caffe"
  }

  stages {
    stage('Checkout') {
      steps {
        checkout scm
      }
    }

    stage('Cleanup') {
      steps {
        sh '''
          docker compose down -v --remove-orphans || true
          docker rm -f rabbitmq || true
        '''
      }
    }

    stage('Compose Build & Up') {
      steps {
        sh '''
          set -e
          docker compose up -d --build rabbitmq caffe-main order-service audit-service
        '''
      }
    }
  }

  post {
    always {
      sh 'docker compose ps || true'
    }
  }
}
