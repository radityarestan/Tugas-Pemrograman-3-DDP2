package assignments.assignment3;

/**
 * Kelas yang mengextend dari kelas exception.
 */
public class BelumTertularException extends Exception	{
    /**
     * Constructor kelas BelumTertularException.
     * @param errorMessage -> error message yang ingin ditampilkan.
     */
    public BelumTertularException(String errorMessage)	{
        super(errorMessage);
    }
}