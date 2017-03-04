package exception;

import adapter.FixAuto;
import model.Automobile;

/**
 * 18-641 Java Smart Phone Development.
 * Project 1
 */
/**
 * The class for AutoException.
 */
public class AutoException extends Exception implements FixAuto {
    /**
     * serialVersionUID.
     */
    private static final long serialVersionUID = 1L;
    /**
     * Error number.
     */
    private int errorno;
    /**
     * Error message.
     */
    private String errormsg;

    /**
     * AutoException constructor.
     */
    public AutoException() {
        super();
        errorInfor(0);
        printmyproblem();

    }

    public enum Error {
        MissingFileName(1, "Missing File Name"), MissingName(2, "Missing Name"), MissingBasePrice(3,
                "Missing Base Price"), MissingOptionSetData(4, "Missing OptionSet Data"), MissingOptionData(5,
                        "Missing Option Data");
        private int num;
        private String s;

        private Error(int num, String s) {
            this.num = num;
            this.s = s;
        }

        public int getnum() {
            return num;
        }

        public String getMessage() {
            return s;
        }
    }

    public void errorInfor(int number) {
        switch (number) {
            case 1: {
                errorno = Error.MissingFileName.getnum();
                errormsg = Error.MissingFileName.getMessage();
                break;
            }
            case 2: {
                errorno = Error.MissingName.getnum();
                errormsg = Error.MissingName.getMessage();
                break;
            }
            case 3: {
                errorno = Error.MissingBasePrice.getnum();
                errormsg = Error.MissingBasePrice.getMessage();
                break;
            }
            case 4: {
                errorno = Error.MissingOptionSetData.getnum();
                errormsg = Error.MissingOptionSetData.getMessage();
                break;
            }
            case 5: {
                errorno = Error.MissingOptionData.getnum();
                errormsg = Error.MissingOptionData.getMessage();
                break;
            }
            default: {
                errorno = 0;
                errormsg = "Undefined Exception";
            }
        }
    }

    /**
     * AutoException constructor.
     * @param errorno
     *            Error number
     */
    public AutoException(int errorno) {
        super();
        this.errorno = errorno;
        errorInfor(errorno);
        printmyproblem();
    }

    /**
     * Print Exception.
     */
    public void printmyproblem() {
        System.out.println("AutoException [errorno=" + errorno + ", errormsg=" + errormsg);
    }

    /**
     * Fix Exception.
     * @param model
     *            Automobile object
     */
    public void fix(Automobile auto) {
        Helper helper = new Helper();
        helper.fixPrice(auto);
    }

    /**
     * Get error number.
     * @return error number
     */
    public int getErrorno() {
        return errorno;
    }

    /**
     * Set error number.
     * @param errorno
     *            error number
     */
    public void setErrorno(int errorno) {
        this.errorno = errorno;
    }

    /**
     * Get error message.
     * @return error message
     */
    public String getErrormsg() {
        return errormsg;
    }

    /**
     * Set error message.
     * @param errormsg
     *            error message
     */
    public void setErrormsg(String errormsg) {
        this.errormsg = errormsg;
    }

}
