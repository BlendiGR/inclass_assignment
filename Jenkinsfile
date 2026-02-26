pipeline {
    agent any

    tools {
        maven 'Maven3'
        docker "Docker"
    }

  environment {
     DOCKERHUB_CREDENTIALS_ID = 'docker_hub'
     DOCKERHUB_REPO = 'blendigr/blendi_test'
     DOCKER_IMAGE_TAG = 'latest'
     PATH = "/usr/local/bin:$PATH"
  }

   stages{
      stage('check'){
          steps {
              git branch: 'main', url: 'https://github.com/BlendiGR/inclass_assignment.git'
         }
      }

      stage('build job: '){
          steps {
            sh  'mvn clean install'
          }
      }
      stage('test'){
          steps {
            sh 'mvn test'
          }
      }
      stage('Report'){
          steps {
               sh 'mvn jacoco:report'
          }
      }

      stage('Publish Test Results') {
             steps {
                junit '**/target/surefire-reports/*.xml'
             }
      }
      stage('Publish Coverage Report') {
              steps {
                  jacoco()
              }
      }

      stage('Build Docker Image') {
          steps {
              sh '/usr/local/bin/docker build -t blendigr/blendi_test:latest .'
          }
      }

      stage('Push to Docker Hub') {
          steps {
              script {
                  docker.withRegistry('https://index.docker.io/v1/', 'docker_hub') {
                      sh "docker push blendigr/blendi_test:latest"
                  }
              }
          }
 }



   }
}