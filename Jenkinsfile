pipeline {
    agent any

    tools {
        maven 'Maven3'
    }

    environment {
        DOCKERHUB_CREDENTIALS_ID = 'docker_hub'
        DOCKERHUB_REPO = 'blendigr/blendi_test'
        DOCKER_IMAGE_TAG = 'latest'
        PATH = "/usr/local/bin:/opt/homebrew/bin:$PATH"
    }

    stages {
        stage('Checkout') {
            steps {
                git branch: 'main', url: 'https://github.com/BlendiGR/inclass_assignment.git'
            }
        }

        stage('Build & Test') {
            steps {
                sh 'mvn clean install'
            }
        }

        stage('Report Generation') {
            steps {
                sh 'mvn jacoco:report'
            }
        }

        stage('Publish Results') {
            steps {
                junit '**/target/surefire-reports/*.xml'
                jacoco()
            }
        }

        stage('Build & Push Docker Image') {
            steps {
                withCredentials([usernamePassword(credentialsId: "${env.DOCKERHUB_CREDENTIALS_ID}", usernameVariable: 'DOCKER_USER', passwordVariable: 'DOCKER_PASS')]) {
                    sh '''
                        echo "$DOCKER_PASS" | docker login -u "$DOCKER_USER" --password-stdin
                        docker build -t ${DOCKERHUB_REPO}:${DOCKER_IMAGE_TAG} .
                        docker push ${DOCKERHUB_REPO}:${DOCKER_IMAGE_TAG}
                    '''
                }
            }
        }
    }

    post {
        always {
            script {
                sh "docker rmi ${env.DOCKERHUB_REPO}:${env.DOCKER_IMAGE_TAG} || true"
            }
        }
    }
}