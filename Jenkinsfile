pipeline {
    agent any

    stages {
        stage('Build') {
            steps {
                echo 'Building..'
                 sh "./gradlew assembleDebug"
            }
        }
        stage('Test') {
            steps {
                echo 'Testing..'
                sh 'make check || true'
                sh "./gradlew testDebugUnitTest"
                junit testResults: '**/build/test-reports/*.xml'
            }
        }
        stage('Deploy') {
            steps {
                echo 'Deploying....'
            }
        }
    }
}