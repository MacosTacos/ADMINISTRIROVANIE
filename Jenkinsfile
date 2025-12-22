pipeline {
  agent any

  environment {
    COMPOSE_PROJECT_NAME = "caffe"
    CONTAINERS = "rabbitmq caffe-main order-service audit-service"
  }

  triggers {

          pollSCM('H/2 * * * *')

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
          docker rm -f ${CONTAINERS} || true
        '''
      }
    }

    stage('Compose Build & Up') {
      steps {
        sh '''
          set -e
          docker compose up -d --build ${CONTAINERS}
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
