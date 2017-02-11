package dnarvaez27.currency;

import java.text.NumberFormat;
import java.util.Locale;

import javax.swing.text.JTextComponent;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class CurrencyListener extends KeyAdapter
{
	private JTextComponent textComponent;
	
	public CurrencyListener( JTextComponent textComponent )
	{
		this.textComponent = textComponent;
	}
	
	@Override
	public void keyReleased( KeyEvent e )
	{
		try
		{
			boolean vacio = textComponent.getText( ).trim( ).isEmpty( );
			boolean symbol = textComponent.getText( ).trim( ).equals( "$" );
			if( symbol || vacio )
			{
				textComponent.setText( "$ 0" );
			}
			
			if( !textComponent.getText( ).equals( "" ) && !textComponent.getText( ).equals( "$" ) )
			{
				textComponent.setText( format( textComponent.getText( ) ) );
			}
		}
		catch( Exception e1 )
		{
			System.out.println( e1.getMessage( ) );
		}
	}
	
	@Override
	public void keyTyped( KeyEvent e )
	{
		char c = e.getKeyChar( );
		
		boolean range = ( c >= '0' ) && ( c <= '9' );
		boolean space = ( c == KeyEvent.VK_BACK_SPACE );
		boolean delete = ( c == KeyEvent.VK_DELETE );
		
		if( !( range || space || delete ) )
		{
			e.consume( );
		}
	}
	
	private String format( String valor )
	{
		String text = valor.replace( "$", "" );
		text = text.replace( " ", "" );
		double num = Double.parseDouble( text.replace( ",", "" ) );
		String temp = NumberFormat.getNumberInstance( Locale.US ).format( num );
		valor = "$ " + temp;
		
		return valor;
	}
}