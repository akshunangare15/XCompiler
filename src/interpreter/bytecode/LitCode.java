package interpreter.bytecode;

import interpreter.VirtualMachine;

public class LitCode extends ByteCode {
    private int value;
    private String id;
    public LitCode(){}

    @Override
    public void init(String args) {
        String argList[] = args.split("\\s");
        value = Integer.parseInt(argList[0]);
        if (argList.length > 1)
            id = argList[1];
    }

    @Override
    public void execute(VirtualMachine vm) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public String getArgs() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
<<<<<<< HEAD
=======

    @Override
    public boolean equals(ByteCode code) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

>>>>>>> 8eccb4f90a5bb6446929eef5cb449ede59553f1d
}
