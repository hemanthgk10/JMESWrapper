package org.wrapper;


public final class SyntaxException extends RuntimeException {

	  private static final long serialVersionUID = 1L;
	  
	  public SyntaxException(String msg) {
		    super(msg);
      }
	  
	  public SyntaxException(String msg, Throwable cause) {
		    super(msg, cause);
	  }
	  
	  public SyntaxException(Throwable cause) {
		    super(cause);
	  }
}

