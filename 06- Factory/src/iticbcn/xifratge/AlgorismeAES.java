package iticbcn.xifratge;

import iticbcn.xifratge.AlgorismeFactory;

public class AlgorismeAES  extends AlgorismeFactory{
    
    @Override
    public Xifrador creXifrador(){
        xifrador x = new XifradorAES();
        return x;
    }
}
