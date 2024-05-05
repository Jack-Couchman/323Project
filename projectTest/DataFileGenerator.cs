using Oracle.ManagedDataAccess.Client;
using System;
using System.Collections.Generic;
using System.Data;
using System.Diagnostics;
using System.IO;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using static System.Windows.Forms.LinkLabel;
using static System.Windows.Forms.VisualStyles.VisualStyleElement.Rebar;

namespace projectTest
{
    internal class DataFileGenerator
    {
        public DataFileGenerator(int itemCount)
        {
            Random rand = new Random();
            int[] stackSizes = { 1, 16, 64 };
            StreamWriter sw = new StreamWriter("H:\\temp.txt");
            //generates the items
            for (int i = 0; i < itemCount; i++)
            {

                sw.WriteLine("insert into Item values ('Item" + i + "'," + stackSizes[rand.Next(3)] + ")");


            }
            Debug.WriteLine("Items Generated");
            //generates the tags
            for (int i = 0; i < itemCount / 5; i++)
            {

                sw.WriteLine("insert into Tag values ('Tag" + i + "')");


            }
            Debug.WriteLine("Tags Generated");
            //generates the relations between items and tags randomly
            for (int i = 0; i < itemCount; i++)
            {
                int randomTagNo = rand.Next(itemCount / 5);

                sw.WriteLine("select * from ItemToTag where itemName like 'Item" + i + "' and tagName like 'Tag" + randomTagNo + "'");

            }
            Debug.WriteLine("ItemsToTag Generated");
            //generates the sources
            for (int i = 0; i < itemCount; i++)
            {
                //adds the total amount of required sources
                for (int a = 1; a <= 4; a++)
                {

                    sw.WriteLine("insert into ItemSource values ('Source" + a + "','Item" + i + "'," + rand.Next(100) + "," + rand.Next(5) + ")");
                }
                //adds individual sources to respective source tables
                sw.WriteLine("insert into Recipe values ('Source1','Item" + i + "','Crafting')");

                //adds random inputs to the recipes
                int randomItemNo = rand.Next(itemCount);
                sw.WriteLine("insert into Input values (''Source1','Item" + i + "','Item" + randomItemNo + "','Crafting')");

                sw.WriteLine("insert into Mining values ('Source2','Item" + i + "')");

                //adds random inputs to blockToMine
                randomItemNo = rand.Next(itemCount);
                sw.WriteLine("insert into BlockToMine values (''Source2','Item" + i + "','Item" + randomItemNo + "')");

                sw.WriteLine("insert into Chest values ('Source3','Item" + i + "','Structure1')");

                sw.WriteLine("insert into Location values ('Source4','Item" + i + "'," + rand.Next(-64, 256) + ",'Biome1')");

            }
            Debug.WriteLine("Sources Generated");
            sw.Close();

            Debug.WriteLine("File Generated");

        }
    }
}
