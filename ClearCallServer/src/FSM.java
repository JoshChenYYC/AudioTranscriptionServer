public class FSM {

    enum State {
        START,
        WAITING_FOR_DATA,
        SEND_TO_AUDIO_TRANSCRIPTION_MODEL,
        TRANSCRIBING_AUDIO,
        SEND_TO_CLASSIFICATION_MODEL,
        CLASSIFYING_TEXT,
        SEND_TO_USER,
        EXIT
    }

    private State currentState;

    public FSM() {
        this.currentState = State.START;
    }

    public void run() {
        while (currentState != State.EXIT) {
            switch (currentState) {
                case START:
                    initialize();
                    currentState = State.WAITING_FOR_DATA;
                    break;

                case WAITING_FOR_DATA:
                    waitForData();
                    currentState = State.SEND_TO_AUDIO_TRANSCRIPTION_MODEL;
                    break;

                case SEND_TO_AUDIO_TRANSCRIPTION_MODEL:
                    sendToAudioTranscription();
                    currentState = State.TRANSCRIBING_AUDIO;
                    break;

                case TRANSCRIBING_AUDIO:
                    transcribeAudio();
                    currentState = State.SEND_TO_CLASSIFICATION_MODEL;
                    break;

                case SEND_TO_CLASSIFICATION_MODEL:
                    sendToClassificationModel();
                    currentState = State.CLASSIFYING_TEXT;
                    break;

                case CLASSIFYING_TEXT:
                    double fraudScore = classifyText();
                    if (fraudScore > 0.8) {
                        currentState = State.SEND_TO_USER; 
                    } else {
                        currentState = State.WAITING_FOR_DATA;
                    }
                    break;

                case SEND_TO_USER:
                    sendToUser();
                    currentState = State.WAITING_FOR_DATA; 
                    break;
            }
        }
        System.out.println("Server closing!");
    }

    // State Methods
    private void initialize() {
        System.out.println("State: START → Initializing FSM...");
    }

    private void waitForData() {
        System.out.println("State: WAITING_FOR_DATA → Waiting for audio data...");
        simulateDataReceived();
    }

    private void sendToAudioTranscription() {
        System.out.println("State: SEND_TO_AUDIO_TRANSCRIPTION_MODEL → Sending audio to transcription model...");
    }

    private void transcribeAudio() {
        System.out.println("State: TRANSCRIBING_AUDIO → Transcribing audio...");
        String text = transcribe();
        System.out.println("Transcribed Text: " + text);
    }

    private void sendToClassificationModel() {
        System.out.println("State: SEND_TO_CLASSIFICATION_MODEL → Sending text to classification model...");
    }

    private double classifyText() {
        System.out.println("State: CLASSIFYING_TEXT → Classifying text...");
        double fraudScore = classify();
        System.out.println("Scam Likelihood: " + fraudScore);
        return fraudScore;
    }

    private void sendToUser() {
        System.out.println("State: SEND_TO_USER → Sending fraud score to the user...");
        notifyUser();
    }

    // Helper Methods (Simulated Logic)
    private void simulateDataReceived() {
        System.out.println("Audio Data Received!");
    }

    private String transcribe() {
        return "This is a potential scam call";
    }

    private double classify() {
        return Math.random(); // Simulated fraud score
    }

    private void notifyUser() {
        System.out.println("ALERT: Scam call detected! Take precautions.");
    }

    // Main Method
    public static void main(String[] args) {
        FSM fsm = new FSM();
        System.out.println("=== ClearCall FSM Simulation ===");
        fsm.run();
    }
}
