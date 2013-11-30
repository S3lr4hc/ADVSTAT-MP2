package mainpackage;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JFrame;
import net.miginfocom.swing.MigLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JButton;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.LineAndShapeRenderer;
import org.jfree.chart.renderer.category.StandardBarPainter;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;

import javax.swing.border.TitledBorder;
import javax.swing.UIManager;


public class View extends JFrame{
	private JTextField txtPolynomial;
	private JTextField txtBX0;
	private JTextField txtX1;
	private JTextField txtBIterations;
	private JTextField txtTolerance;
	private JTextField txtNX0;
	private JTextField txtNIterations;
	private JButton btnComputeBisection;
	private JButton btnComputeNewton;
	private JPanel contentPane;
	private JLabel lblOutputBisection;
	private JLabel lblOutputNewton;
	private JPanel pnlChartSimulation;
	private JFreeChart chart;
	private ChartPanel chartPanel;
	public View() {
		setTitle("Numerical Analysis Simulation");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		setBounds(0,0,800,600);
		setLocationRelativeTo(null);
		addComponents();
	
	}
	
	private void addComponents()
	{
		contentPane=new JPanel();
		//contentPane.setVisible(true);
		contentPane.setSize(780,580);
		
		contentPane.setLayout(new MigLayout("", "[][grow][grow][]", "[][][][][][][][][][][grow]"));
		
		JLabel lblPolynomial = new JLabel("Polynomial:");
		contentPane.add(lblPolynomial, "cell 0 1,alignx trailing");
		
		txtPolynomial = new JTextField();
		contentPane.add(txtPolynomial, "cell 1 1,growx");
		txtPolynomial.setColumns(10);
		
		JLabel lblBisection = new JLabel("Bisection:");
		contentPane.add(lblBisection, "cell 0 3");
		
		JLabel lblNewton = new JLabel("Newton's: ");
		contentPane.add(lblNewton, "cell 3 3");
		
		JLabel lblBX0 = new JLabel("x0");
		contentPane.add(lblBX0, "cell 0 4,alignx trailing");
		
		txtBX0 = new JTextField();
		contentPane.add(txtBX0, "cell 1 4");
		txtBX0.setColumns(10);
		
		txtNX0 = new JTextField();
		contentPane.add(txtNX0, "cell 2 4,alignx right");
		txtNX0.setColumns(10);
		
		JLabel lblNX0 = new JLabel("x0");
		contentPane.add(lblNX0, "cell 3 4");
		
		JLabel lblX1 = new JLabel("x1");
		contentPane.add(lblX1, "cell 0 5,alignx trailing");
		
		txtX1 = new JTextField();
		contentPane.add(txtX1, "cell 1 5");
		txtX1.setColumns(10);
		
		txtNIterations = new JTextField();
		txtNIterations.setColumns(10);
		contentPane.add(txtNIterations, "cell 2 5,alignx right");
		
		JLabel lblNIterations = new JLabel("Iterations");
		contentPane.add(lblNIterations, "cell 3 5");
		
		JLabel lblBIterations = new JLabel("Iterations");
		contentPane.add(lblBIterations, "cell 0 6,alignx trailing");
		
		txtBIterations = new JTextField();
		contentPane.add(txtBIterations, "cell 1 6");
		txtBIterations.setColumns(10);
		
		JLabel lblTolerance = new JLabel("Tolerance");
		contentPane.add(lblTolerance, "cell 0 7,alignx trailing");
		
		txtTolerance = new JTextField();
		contentPane.add(txtTolerance, "cell 1 7");
		txtTolerance.setColumns(10);
		
		btnComputeBisection = new JButton("Compute");
		contentPane.add(btnComputeBisection, "flowx,cell 1 8");
		
		btnComputeNewton = new JButton("Compute");
		contentPane.add(btnComputeNewton, "cell 2 8,alignx right");
		
		lblOutputBisection = new JLabel("");
		contentPane.add(lblOutputBisection, "cell 1 9");
		
		lblOutputNewton = new JLabel("");
		contentPane.add(lblOutputNewton, "cell 2 9");
		setContentPane(contentPane);
		
		pnlChartSimulation = new JPanel();
		pnlChartSimulation.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Graph and Simulation", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		contentPane.add(pnlChartSimulation, "cell 0 10 4 1,grow");
		pnlChartSimulation.setVisible(true);
		
		chartPanel=new ChartPanel(chart);
	}

	public void updateChart(JFreeChart chart, ChartPanel chartPanel, int x, int y)
	{
		  if(chartPanel!=null)
	    	  pnlChartSimulation.remove(chartPanel);

		  //chartPanel = new ChartPanel(chart);
		  chartPanel.setChart(chart);
		  chartPanel.setBounds(x, y, 500, 250);
		  pnlChartSimulation.add(chartPanel);
		  repaint();
	}
	
	public void createChart(CategoryDataset dataset, String title,JFreeChart chart,ChartPanel chartPanel, int x, int y)
	{
		chart = ChartFactory.createLineChart(
		title, "X","f(X)" , dataset,
		PlotOrientation.VERTICAL, false, true, false);
				  
		//Customization of bar graph
		chart.getTitle().setFont(new Font("Tahoma",Font.PLAIN,20));
				  
		final CategoryPlot plot = chart.getCategoryPlot();
		plot.setBackgroundPaint(Color.white);
		plot.getDomainAxis().setLabelFont(new Font("Tahoma",Font.PLAIN,15));
		plot.getRangeAxis().setLabelFont(new Font("Tahoma",Font.PLAIN,15));
			      
		final LineAndShapeRenderer renderer = (LineAndShapeRenderer) plot.getRenderer();
			      
		renderer.setDrawOutlines(true);
		renderer.setBaseShapesVisible(true);
		renderer.setItemMargin(0.2);
		renderer.setSeriesPaint(0, Color.green);
		updateChart(chart, chartPanel, x, y);
	}
	
	/*Functions to interface with controller*/
	public void addBisectionListener(ActionListener listener)
	{
		btnComputeBisection.addActionListener(listener );
	}
	public void addNewtonListener(ActionListener listener)
	{
		btnComputeNewton.addActionListener(listener);
	}
	public ArrayList<Term> getTerms() //the content of this may change as better input method is developed
	{
		ArrayList<Term>terms=new ArrayList<Term>();
		double tempCoefficient;
		int tempPower;
		int i=0;
		
		String input=txtPolynomial.getText();
		String[]parts=input.split(" ");
		if(parts.length%2==0)
		{
		while(i<parts.length)
		{
				tempCoefficient = Double.parseDouble(parts[i]);
				tempPower=Integer.parseInt(parts[i+1]);
				terms.add(new Term(tempCoefficient, tempPower));
				i+=2;
		}
		}
		
		return terms;
	}
	public double getBX0()
	{
		return Double.parseDouble(txtBX0.getText());
	}
	public double getX1()
	{
		return Double.parseDouble(txtX1.getText());
	}
	public int getBIterations()
	{
		return Integer.parseInt(txtBIterations.getText());
	}
	public double getTolerance()
	{
		return Double.parseDouble(txtTolerance.getText());
	}
	public double getNX0()
	{
		return Double.parseDouble(txtNX0.getText());
	}
	public int getNIterations()
	{
		return Integer.parseInt(txtNIterations.getText());
	}
	public void setBisectionResult(ArrayList<double[]> result) 
	{
		for(double[] interval : result)
		{
		//lblOutputBisection.setText(lblOutputBisection.getText()+"\n"+Double.toString(interval[0])+", "+Double.toString(interval[1]));
		//TODO: set up a table model based on list of results
		}
	}
	public void setNewtonResult(ArrayList<Double> result)
	{
		for(double xi : result)
		{
			//lblOutputNewton.setText(lblOutputNewton.getText()+", \n"+Double.toString(xi));
			//TODO: set up a table model based on list of results
		}
		
	}
	public JFreeChart getChart() {
		return chart;
	}

	public void setChart(JFreeChart chart) {
		this.chart = chart;
	}

	public ChartPanel getChartPanel() {
		return chartPanel;
	}

	public void setChartPanel(ChartPanel chartPanel) {
		this.chartPanel = chartPanel;
	}
	
}
