import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

public class JavaScriptCall {
	public static void main(String[] args) throws ScriptException {
		
		
	ScriptEngineManager mngr= new ScriptEngineManager();
	ScriptEngine engine=mngr.getEngineByName("javascript");

//String name = "Prompt(Enter your details : )";

//String name2="funtion print() { console.log('Prabhjeet');};";
//engine.eval(name);
engine.eval("funtion print() { console.log('Prabhjeet');};");
	}
}