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

    stage('Compose Build & Up') {
      steps {
        sh '''
          set -e
          docker compose up -d --build
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
