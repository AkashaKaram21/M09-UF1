package iticbcn.xifratge;

import iticbcn.xifratge.AlgorismeFactory;

public class AlgorismeMonoalfabetic extends AlgorismeFactory {
    @Override
    public xifratge creXifrador(){
        xifrar c = new AlgorismeMonoalfabetic();

        return c;
    }
}
