package frontend.controller;

import protocols.otp.OperatorSenderDTO;
import protocols.otp.OperatorResultDTO;
import controller.OperationEndpoint;

public final class OTPController
{
    public static double chamar(double valor1, double valor2, String operacao) throws BaseError
    {
        OperatorSenderDTO requisicao = new OperatorSenderDTO(valor1, valor2, operacao);
        OperationEndpoint endpoint = new OperationEndpoint();
        OperatorResultDTO response = endpoint.call(requisicao);

        if(response.erro == null)
            return response.resultado;

        throw new BaseError(response.erro);
    }
}

