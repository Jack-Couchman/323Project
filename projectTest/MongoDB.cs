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
        //initialising variables to be used in various methods
        List<FilterDefinition<BsonDocument>> filterList = new List<FilterDefinition<BsonDocument>>();
        static MongoClient dbClient = new MongoClient("mongodb://jp247:eYbU7423CQ@mongodb.cms.waikato.ac.nz:27017/?authSource=admin");
        static IMongoDatabase db = dbClient.GetDatabase("jp247");
        IMongoCollection<BsonDocument> table;
        IAggregateFluent<BsonDocument> aggregate;

        public MongoDB()
        {
            InitializeComponent();
        }

        private void MongoDB_Load(object sender, EventArgs e)
        {
            labelError.Text = "";
            listBox1.Items.Clear();
            comboBoxType.Items.Clear();
            comboBoxType.Items.Add("String");
            comboBoxType.Items.Add("Int");
            comboBoxType.SelectedIndex = 0;

            comboBoxOp.Items.Clear();
            comboBoxOp.Items.Add("%Match");
            comboBoxOp.Items.Add("%Sort");
            comboBoxOp.SelectedIndex = 0;

            table = db.GetCollection<BsonDocument>("empty");
            aggregate = table.Aggregate();
        }

        private void buttonSearch_Click(object sender, EventArgs e)
        {
            try
            {
                labelError.Text = "";
                listBox1.Items.Clear();

                List<BsonDocument> doc = aggregate.ToList();
                foreach (var result in doc)
                {
                    listBox1.Items.Add(result);
                }
            }
            catch (Exception ex)
            {
                labelError.Text = ex.Message;
            }
        }

        private void buttonSwitch_Click(object sender, EventArgs e)
        {
            Oracle oracle = new Oracle();
            oracle.Show();
            this.Hide();
        }

        private void buttonClear_Click(object sender, EventArgs e)
        {
            filterList.Clear();
            table = db.GetCollection<BsonDocument>("empty");
            aggregate = table.Aggregate();
        }

        private void buttonAdd_Click(object sender, EventArgs e)
        {
            labelError.Text = "";
            listBox1.Items.Clear();
            table = db.GetCollection<BsonDocument>(textBoxCollection.Text.ToString());
            aggregate = table.Aggregate();

            if (comboBoxOp.SelectedIndex == 0)
            {
                if (comboBoxType.SelectedIndex == 0)
                {
                    String condition = textBoxCondition.Text.ToString();
                    var filter = Builders<BsonDocument>.Filter.Eq(textBoxAttribute.Text.ToString(), condition);
                    filterList.Add(filter);

                }
                else if (comboBoxType.SelectedIndex == 1)
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
        }

        private void buttonView_Click(object sender, EventArgs e)
        {
            listBox1.Items.Clear();
            foreach(FilterDefinition<BsonDocument> filter in filterList)
            {
                listBox1.Items.Add(filter.ToString());
            }
        }
    }
}
