pipeline {
    agent any
    tools {
        maven 'Maven 3.6.3'
        jdk 'jdk11'
    }
    stages {
        stage ('Initialize') {
            steps {
                script{
                numbers = load "numbers.groovy"
                numbers.printSomeNumbers()
                numbers.printSomeNumbers2()
                numbers.printSomeNumbers3()
                numbers.printSomeNumbers4()
                numbers.printSomeNumbers5()
                numbers.printSomeNumbers6()
                
                }
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
