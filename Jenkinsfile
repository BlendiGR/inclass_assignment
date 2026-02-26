pipeline {
    agent any

    tools {

        Maven3

    }

  environment {
     DOCKERHUB_CREDENTIALS_ID = 'Docker_Hub'
     DOCKERHUB_REPO = 'blendigr/blendi_test'
     DOCKER_IMAGE_TAG = 'latest'
  }

   stages{
      stage('check'){
          steps {
              git url: ''
         }
      }

      stage('build job: '){
          steps {
            bat  'mvn clean install'
          }
      }
      stage('test'){
          steps {
            bat 'mvn test'
          }
      }
      stage('Report'){
          steps {
               bat 'mvn jacoco:report'
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
                       script {
                           docker.build("${DOCKERHUB_REPO}:${DOCKER_IMAGE_TAG}")
                       }
                    }
               }

               stage('Push Docker Image to Docker Hub') {
                        steps {
                            script {
                                docker.withRegistry('https://index.docker.io/v1/', DOCKERHUB_CREDENTIALS_ID) {
                                    docker.image("${DOCKERHUB_REPO}:${DOCKER_IMAGE_TAG}").push()
                                }
                            }
                        }
               }



   }
}