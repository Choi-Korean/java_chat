package exception;

public class NotFoundUserNameException extends Exception{
	public NotFoundUserNameException () {}
	public NotFoundUserNameException (String m) {
		super(m);
	}
}
