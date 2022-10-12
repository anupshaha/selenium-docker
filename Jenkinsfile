pipeline {
    // master executor should be set to 0
    agent any
    stages {
        stage('Build Jar') {
            docker {
                image 'maven:3-alpine'
                args '-v C:/Users/anshaha/.m2:/root/.m2'
            }
            steps {
                //sh
                bat "mvn clean package -DskipTests"
            }
        }
        stage('Build Image') {
            steps {
                //sh
                bat "docker build -t anupshaha/selenium-docker ."
            }
        }
        stage('Push Image') {
            steps {
			    withCredentials([usernamePassword(credentialsId: 'a53b77a5-57f3-4326-b6cc-aa9cc2c3c5b8', passwordVariable: 'pass', usernameVariable: 'user')]) {
                    //sh
			        bat "docker login --username=${user} --password=${pass}"
			        bat "docker push anupshaha/selenium-docker:latest"
			    }
            }
        }
    }
}