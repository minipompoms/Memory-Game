package main;

public class Card {
    private int id;
    private String name;
    private State state;

    public Card() {
        state = State.hidden;

    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }


	@Override
	public String toString() {
		return "Card [id=" + id + ", name=" + name + ", state=" + state + "]";
	}

}
