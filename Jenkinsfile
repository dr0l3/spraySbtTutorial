node {

  def testImage = docker.image('hseeberger/scala-sbt')
  testImage.pull
  testImage.inside {
    checkout scm

    stage "install dependencies"
       sh "sbt update"

    stage "compile test"
       sh "sbt compile test"
  }

  stage "commit and push"
  testImage.inside {
    stage "run"
        sh "sbt run"
  }

  testImage.commit('droletours:sbtDockerJenkins').push()

  stage "deploy"
  sh './deploy.sh'
}