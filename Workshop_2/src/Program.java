import controller.Master;
//import model.Authenticator;
import model.Registry;
import view.Console;

public class Program
{

	public static void main(String[] args) throws Exception
	{
		Console console = new Console();
		Registry registry = new Registry();

		registry = registry.load();
		Master master = new Master();
		master.run(registry, console);
	}

}
