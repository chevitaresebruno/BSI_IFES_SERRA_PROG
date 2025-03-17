package protocols.titp;


public class MultiTextInformationDTO
{
    private final String[] information;

    public MultiTextInformationDTO(String[] information)
    {
        this.information = information;
    }

    public String[] getInformation()
    {
        return this.information;
    }
}
