pipeline {

    agent any

    stages {

        stage('Checkout') {
            steps {
                checkout scm
            }
        }

        stage('Build') {
            steps {
                bat 'mvn clean test package'
            }
        }

        stage('Deploy to Tomcat') {
            steps {
                bat '''
                copy /Y target\\cloud-kitchen-0.0.1-SNAPSHOT.war "C:\\Program Files\\Apache Software Foundation\\Tomcat 11.0\\webapps\\cloud-kitchen.war"
                '''
            }
        }
    }
}