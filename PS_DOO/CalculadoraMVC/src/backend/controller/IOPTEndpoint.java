package controller;

import protocols.otp.OperatorResultDTO;
import protocols.otp.OperatorSenderDTO;


public interface IOPTEndpoint
{
    public OperatorResultDTO call(OperatorSenderDTO requisicao);
}

