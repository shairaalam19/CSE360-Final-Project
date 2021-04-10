import javax.swing.JFrame;
import javax.swing.JTabbedPane;

public class VaccineViewBundler
{
    //Home home;
    //SaveData saveData;
    private AddData addData;
    private LoadData loadData;
    private About about;
    private VaccineController controller;

    public VaccineViewBundler()
    {
		//instantiate view panes
        addData = new AddData();
        loadData = new LoadData();
        about = new About();

		//set view bundlers for eahc of the view panes
		addData.setBundler(this);
		loadData.setBundler(this);

		JFrame frame = new JFrame ("Vaccinations");
		frame.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);

		// add all panels to TabPane
		JTabbedPane tabPane = new JTabbedPane();
		//tabPane.addTab("Home", home);
		tabPane.addTab("Add Data", addData);
		tabPane.addTab("Load Data", loadData);
		tabPane.addTab("About", about);

		// set size of frame
		tabPane.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);
//		tabPane.setBounds(50,50,200,200);
		frame.add(tabPane);
		frame.setSize(400,400);
		frame.setVisible(true);

    }

	public void setController(VaccineController controller) {
		this.controller = controller;
	}

	public AddData getAddData() {
		return addData;
	}

	public void setAddData(AddData addData) {
		this.addData = addData;
	}

	public LoadData getLoadData() {
		return loadData;
	}

	public void setLoadData(LoadData loadData) {
		this.loadData = loadData;
	}

	public About getAbout() {
		return about;
	}

	public void setAbout(About about) {
		this.about = about;
	}

	public VaccineController getController() {
		return this.controller;
	}
}