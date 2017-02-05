node {
    stage "prepare environment" {
      checkout scm
      docker.image('hseeberger/scala-sbt').inside {
        stage "install dependencies"
            sh "sbt update"

        stage "compile test"
            sh "sbt compile test"
      }
    }
}