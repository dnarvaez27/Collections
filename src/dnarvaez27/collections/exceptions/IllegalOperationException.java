package dnarvaez27.collections.exceptions;

public class IllegalOperationException extends Error
{
	private static final long serialVersionUID = -4610842382201378546L;
	
	public IllegalOperationException( )
	{
		super( );
	}
	
	public IllegalOperationException( String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace )
	{
		super( message, cause, enableSuppression, writableStackTrace );
	}
	
	public IllegalOperationException( String message, Throwable cause )
	{
		super( message, cause );
	}
	
	public IllegalOperationException( String message )
	{
		super( message );
	}
	
	public IllegalOperationException( Throwable cause )
	{
		super( cause );
	}
}