node {

  var testImage = docker.image('hseeberger/scala-sbt').inside {
    checkout scm

    stage "install dependencies"
        sh "sbt update"

    stage "compile test"
        sh "sbt compile test"
  }

  stage "commit and push"
  var deployImage = testImage.inside {
    stage "run"
        sh "sbt run"
  }

  deployImage.commit('droletours:sbtDockerJenkins').push()

  stage "deploy"
  sh './deploy.sh'
}