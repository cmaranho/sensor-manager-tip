🌀 Interatividade fluida com sensores no Jetpack Compose

Você já pensou em dar vida aos seus componentes com o movimento do dispositivo?

Recentemente, desenvolvi um WaveCard interativo, que reage ao acelerômetro e ao valor da carteira, simulando uma onda dinâmica que enche à medida que a carteira se aproxima do limite.

💡 Destaques do componente:
	•	🧭 Acelerômetro controla o movimento da onda em tempo real
	•	💸 A altura da onda representa o valor da carteira (proporcional ao limite)
	•	🎨 Tudo animado com Canvas e Jetpack Compose

📱 Isso permite uma UX sensorial e mais visual, ideal para apps de finanças, gamificação ou qualquer interface que deseje ir além do óbvio.
🧠 Internamente, o componente usa Canvas, curvas de Bezier (cubicTo), lerp para a altura, e sensores via SensorManager.
