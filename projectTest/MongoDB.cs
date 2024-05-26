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

using MongoDB.Bson;
using MongoDB.Driver;
using Amazon.Auth.AccessControlPolicy;

namespace projectTest
{
    public partial class MongoDB : Form
    {
        List<FilterDefinition<BsonDocument>> filterList = new List<FilterDefinition<BsonDocument>>();
        public MongoDB()
        {
            InitializeComponent();
        }

        private void MongoDB_Load(object sender, EventArgs e)
        {
            label5.Text = "";
            listBox1.Items.Clear();
            comboBox1.Items.Clear();
            comboBox1.Items.Add("String");
            comboBox1.Items.Add("Int");
            comboBox1.SelectedIndex = 0;

            comboBox2.Items.Clear();
            comboBox2.Items.Add("%Match");
            comboBox2.Items.Add("%Sort");
            comboBox2.SelectedIndex = 0;
        }

        private void button1_Click(object sender, EventArgs e)
        {
            try
            {
                label5.Text = "";
                listBox1.Items.Clear();
                MongoClient dbClient = new MongoClient("mongodb://jp247:eYbU7423CQ@mongodb.cms.waikato.ac.nz:27017/?authSource=admin");
                IMongoDatabase db = dbClient.GetDatabase("jp247");

                var table = db.GetCollection<BsonDocument>(textBoxCollection.Text.ToString());
                var aggregate = table.Aggregate();
                if (comboBox2.SelectedIndex == 0)
                {
                    if (comboBox1.SelectedIndex == 0)
                    {
                        String condition = textBoxCondition.Text.ToString();
                        var filter = Builders<BsonDocument>.Filter.Eq(textBoxAttribute.Text.ToString(), condition);
                        filterList.Add(filter);

                    }
                    else if (comboBox1.SelectedIndex == 1)
                    {
                        int condition = int.Parse(textBoxCondition.Text.ToString());
                        var filter = Builders<BsonDocument>.Filter.Eq(textBoxAttribute.Text.ToString(), condition);
                        filterList.Add(filter);
                    }
                    foreach (FilterDefinition<BsonDocument> filter in filterList)
                    {
                        aggregate = aggregate.Match(filter);
                    }
                }
                List<BsonDocument> doc = aggregate.ToList();
                foreach (var result in doc)
                {
                    listBox1.Items.Add(result);
                }
            }
            catch(Exception ex)
            {
                label5.Text = ex.Message;
            }
        }

        private void button2_Click(object sender, EventArgs e)
        {
            Form1 oracle = new Form1();
            oracle.Show();
            this.Hide();
        }

        private void button3_Click(object sender, EventArgs e)
        {
            filterList.Clear();
        }
    }
}
