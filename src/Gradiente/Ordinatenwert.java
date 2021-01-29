package Gradiente;

public class Ordinatenwert {
	public String name;
	public float x_m,y_m;
	
	public Ordinatenwert(String name, float x_m, float y_m) {
		super();
		this.name = name;
		this.x_m = x_m;
		this.y_m = y_m;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public float getX_m() {
		return x_m;
	}

	public void setX_m(float x_m) {
		this.x_m = x_m;
	}

	public float getY_m() {
		return y_m;
	}

	public void setY_m(float y_m) {
		this.y_m = y_m;
	}
	
	

}
