pipeline {
    agent any

    tools {
        jdk 'JDK17'       // Must match the name you set in Global Tool Configuration
        maven 'Maven3.9'  // Must match your Maven installation name in Jenkins
    }

    environment {
        // You can set environment variables if needed
        REPORT_DIR = "target/extent-reports"
    }

    stages {
        stage('Checkout') {
            steps {
                git branch: 'main',
                    url: 'https://github.com/omkar21012/Java_Selenium_Framework.git'
            }
        }

        stage('Build & Test') {
            steps {
                sh "mvn clean test"
            }
        }

        stage('Publish Report') {
            steps {
                publishHTML(target: [
                    allowMissing: false,
                    alwaysLinkToLastBuild: true,
                    keepAll: true,
                    reportDir: "${REPORT_DIR}",
                    reportFiles: 'index.html',
                    reportName: 'Extent Report'
                ])
            }
        }
    }

    post {
        always {
            archiveArtifacts artifacts: 'target/**/*.html', allowEmptyArchive: true
            junit '**/target/surefire-reports/*.xml'
        }
    }
}
