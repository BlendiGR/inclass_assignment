pipeline {
    agent any

    tools {
        maven 'Maven3'
        dockerTool 'docker-tool'
    }

    environment {
        DOCKERHUB_CREDENTIALS_ID = 'docker_hub'
        DOCKERHUB_REPO = 'blendigr/blendi_test'
        DOCKER_IMAGE_TAG = 'latest'
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
                script {
                    docker.withRegistry('', "${env.DOCKERHUB_CREDENTIALS_ID}") {
                        def customImage = docker.build("${env.DOCKERHUB_REPO}:${env.DOCKER_IMAGE_TAG}")
                        customImage.push()
                    }
                }
            }
        }
    }
}