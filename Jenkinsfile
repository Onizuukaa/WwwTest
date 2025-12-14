pipeline {
	agent any

	stages {
		stage('Czyszczenie i Testy') {
			steps {
				// Używamy 'bat' bo pracujesz na Windowsie.
				// Uruchamiamy testy i ignorujemy błąd (żeby pipeline szedł dalej do sekcji 'post')
				catchError(buildResult: 'SUCCESS', stageResult: 'FAILURE') {
					bat 'gradlew clean test'
				}
			}
		}
	}

	post {
		success {
			slackSend (
				channel: '#automatyka',
				color: 'good',
				message: "✅ SUKCES: Testy w projekcie ${env.JOB_NAME} przeszły pomyślnie! (<${env.BUILD_URL}|Otwórz>)",
				tokenCredentialId: 'slack-token' // To ID klucza, który stworzyliśmy wcześniej w Jenkinsie
			)
		}
		failure {
			slackSend (
				channel: '#automatyka',
				color: 'danger',
				message: "🚨 AWARIA: Testy w projekcie ${env.JOB_NAME} nie powiodły się. (<${env.BUILD_URL}|Otwórz>)",
				tokenCredentialId: 'slack-token'
			)
		}
	}
}