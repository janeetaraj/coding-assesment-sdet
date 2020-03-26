pipeline {
    agent any
    tools {
        maven 'maven-3.6.3'
        jdk 'jdk8'
    }
    stages {
        stage ('Initialize') {
            steps {
                sh '''
                    echo "PATH = ${PATH}"
                    echo "M2_HOME = ${M2_HOME}"
                '''
            }
        }

        stage ('Build') {
            steps {
                sh 'mvn clean test'
            }
            post {
                success {
                    junit 'target/surefire-reports/*.xml'
                }
                always {
                    cucumber buildStatus: 'UNSTABLE',
                            failedFeaturesNumber: 1,
                            failedScenariosNumber: 1,
                            skippedStepsNumber: 1,
                            failedStepsNumber: 1,
                            fileIncludePattern: '**/*cucumber.json',
                            sortingMethod: 'ALPHABETICAL',
                            trendsLimit: 100
                    }
                }
        }
    }
}
