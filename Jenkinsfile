def printSomeNumbers(){
    println(1);
}

pipeline {
    agent any
    tools {
        maven 'Maven 3.6.3'
        jdk 'jdk11'
    }
    stages {
        stage ('Initialize') {
            steps {
                printSomeNumbers()
                sh '''
                    echo "PATH = ${PATH}"
                    echo "M2_HOME = ${M2_HOME}"
                '''
            }
        }

        stage ('Build') {
            steps {
                sh 'cd photopastedemo; mvn -Dmaven.test.failure.ignore=true install' 
            }
        }
    }
}