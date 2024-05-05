using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Diagnostics;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;
using Oracle.ManagedDataAccess.Client;

namespace projectTest
{
    public partial class Form1 : Form
    {
        public Form1()
        {
            InitializeComponent();
        }

        private void Form1_Load(object sender, EventArgs e)
        {
            try
            {
                label1.Text= string.Empty;
                string oradb = "Data Source=oracle.cms.waikato.ac.nz:1521/teaching;User Id=jc529;Password=ykRpEsZMGw;";
                OracleConnection conn = new OracleConnection(oradb);  // C#
                conn.Open();
                OracleCommand cmd = new OracleCommand();
                cmd.Connection = conn;
                cmd.CommandType = CommandType.Text;

                //DataGenerator data = new DataGenerator(cmd, 1000);
                //DataFileGenerator dataFile = new DataFileGenerator(100000);
                conn.Dispose();
            }
            catch (Exception ex)
            {
                Debug.WriteLine(ex.Message);
            }
        }

        private void button1_Click(object sender, EventArgs e)
        {
            try
            {
                label1.Text = string.Empty;
                listBox1.Items.Clear();
                string oradb = "Data Source=oracle.cms.waikato.ac.nz:1521/teaching;User Id=jc529;Password=ykRpEsZMGw;";
                OracleConnection conn = new OracleConnection(oradb);  // C#
                conn.Open();
                OracleCommand cmd = new OracleCommand();
                cmd.Connection = conn;
                cmd.CommandType = CommandType.Text;

                cmd.CommandText = "select "+textBox1.Text+" from "+textBox2.Text;
                if(textBox3.Text.Length > 0)
                {
                    cmd.CommandText += " where " + textBox3.Text;
                }

                OracleDataReader dr = cmd.ExecuteReader();
                while (dr.Read())
                {
                    string text = "";
                    Object[] values = new Object[4];
                    int numColumns = dr.GetValues(values); //after "reading" a row
                    for (int i = 0; i < numColumns; i++)
                    {
                        text += values[i].ToString().PadRight(10);
                    }
                    listBox1.Items.Add(text);
                }
            }
            catch(Exception ex)
            {
                label1.Text = ex.Message;
            }
        }

        private void textBox2_TextChanged(object sender, EventArgs e)
        {

        }
    }
}
