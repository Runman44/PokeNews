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
                sh "./gradlew testDebugUnitTest"
                junit keepLongStdio: true, testResults: '**/build/test-reports/*.xml'
            }
        }
        stage('Deploy') {
            steps {
                echo 'Deploying....'
            }
        }
    }
}