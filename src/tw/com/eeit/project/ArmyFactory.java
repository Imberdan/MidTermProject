package tw.com.eeit.project;

public class ArmyFactory {
		public static InterArmy createArmyFactory() {
			return new ArmyImp();
		}
}