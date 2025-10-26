pipeline {
    agent any
    tools {
        maven 'Maven-3.9.11'
    }
    environment {
        DOCKER_USERNAME = 'kiranjinka341'               
        DOCKER_IMAGE = "${DOCKER_USERNAME}/helloworld-app"  
        DOCKER_TAG = "${BUILD_NUMBER}"
        DOCKER_CREDENTIALS_ID = 'dockerhublogincreds'
    }
    stages {
        stage('Checkout') {
            steps {
                git branch: 'master',
                    url: 'https://github.com/KiranJinka45/docker_helloworld_app.git'
            }
        }
        stage('Maven Build') {
            steps {
                sh 'mvn clean install'
            }
        }
        stage('Build Docker Image') {
            steps {
                echo 'Building Docker Image...'
                sh """
                    docker build -t ${DOCKER_IMAGE}:${DOCKER_TAG} .
                    docker tag ${DOCKER_IMAGE}:${DOCKER_TAG} ${DOCKER_IMAGE}:latest
                """
            }
        }
        stage('Push Docker Image') {
            steps {
                echo 'Pushing Docker image to Docker Hub...'
                withCredentials([usernamePassword(credentialsId: "${DOCKER_CREDENTIALS_ID}", usernameVariable: 'DOCKER_USER', passwordVariable: 'DOCKER_PASS')]) {
                    sh """
                        echo \$DOCKER_PASS | docker login -u \$DOCKER_USER --password-stdin
                        docker push ${DOCKER_IMAGE}:${DOCKER_TAG}
                        docker push ${DOCKER_IMAGE}:latest
                        docker logout
                    """
                }
            }
        }
        stage('Deploy Container') {
            steps {
                echo 'Deploying application container...'
                sh """
                    docker stop helloworld-app || true
                    docker rm helloworld-app || true
                    docker run -d \
                    --name helloworld-app \
                    -p 9090:8080 \
                    ${DOCKER_IMAGE}:${DOCKER_TAG}
                """
            }
        }
    }
    post {
        success {
            echo 'Pipeline completed successfully!'
        }
        failure {
            echo 'Pipeline failed!'
        }
    }
}