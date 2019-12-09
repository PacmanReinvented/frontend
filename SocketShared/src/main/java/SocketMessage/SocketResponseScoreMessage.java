package SocketMessage;

public class SocketResponseScoreMessage extends SocketMessage {

    private String[] scores;


    public SocketResponseScoreMessage(String[] scores) {
        this.scores = scores;
        this.setOperationType(SocketOperationType.SCORE);
    }

    public SocketResponseScoreMessage(){
        this.setOperationType(SocketOperationType.SCORE);
    }

    public String[] getScores() {
        return scores;
    }

    public void setScores(String[] scores) {
        this.scores = scores;
    }
}
