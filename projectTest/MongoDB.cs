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

namespace projectTest
{
    public partial class MongoDB : Form
    {
        public MongoDB()
        {
            InitializeComponent();
        }

        private void MongoDB_Load(object sender, EventArgs e)
        {
            listBox1.Items.Clear();
            comboBox1.Items.Clear();
            comboBox1.Items.Add("String");
            comboBox1.Items.Add("Int");
            comboBox1.SelectedIndex = 0;
        }

        private void button1_Click(object sender, EventArgs e)
        {
            listBox1.Items.Clear();
            MongoClient dbClient = new MongoClient("mongodb://jp247:eYbU7423CQ@mongodb.cms.waikato.ac.nz:27017/?authSource=admin");
            IMongoDatabase db = dbClient.GetDatabase("jp247");

            var table = db.GetCollection<BsonDocument>(textBoxCollection.Text.ToString());
            var filter = Builders<BsonDocument>.Filter.Eq("", "");
            if (comboBox1.SelectedIndex== 0)
            {
                String condition = textBoxCondition.Text.ToString();
                filter = Builders<BsonDocument>.Filter.Eq(textBoxAttribute.Text.ToString(), condition);
            }
            else if (comboBox1.SelectedIndex == 1)
            {
                int condition = int.Parse(textBoxCondition.Text.ToString());
                filter = Builders<BsonDocument>.Filter.Eq(textBoxAttribute.Text.ToString(), condition);
            }

            List<BsonDocument> doc = table.Find(filter).ToList();
            foreach (var result in doc)
            {
                listBox1.Items.Add(result);
            }
        }
    }
}
