node {

  def testImage = docker.image('hseeberger/scala-sbt')
  testImage.pull()
  testImage.inside {
    checkout scm

    stage "install dependencies"
       sh "sbt update"

    stage "compile test"
       sh "sbt compile test"
  }


  docker.build('droletours:testsbt').push()

  stage "deploy"
  sh './deploy.sh'
}