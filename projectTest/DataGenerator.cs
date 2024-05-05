using Oracle.ManagedDataAccess.Client;
using System;
using System.Collections.Generic;
using System.Data;
using System.Diagnostics;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using static System.Windows.Forms.VisualStyles.VisualStyleElement.Rebar;

namespace projectTest
{
    internal class DataGenerator
    {
        public DataGenerator(OracleCommand cmd, int itemCount)
        {
            Random rand = new Random();
            int[] stackSizes = { 1, 16, 64 };
            OracleDataReader dr;

            //generates the items
            for (int i = 0; i < itemCount; i++)
            {
                cmd.CommandText = "insert into Item values ('Item" + i + "'," + stackSizes[rand.Next(3)] + ")";
                cmd.CommandType = CommandType.Text;
                cmd.ExecuteNonQuery();
            }
            Debug.WriteLine("Items Generated");
            //generates the tags
            for (int i = 0; i < itemCount / 5; i++)
            {
                cmd.CommandText = "insert into Tag values ('Tag" + i + "')";
                cmd.CommandType = CommandType.Text;
                cmd.ExecuteNonQuery();
            }
            Debug.WriteLine("Tags Generated");
            //generates the relations between items and tags randomly
            for (int i = 0; i < itemCount; i++)
            {
                try
                {
                    int randomItemNo = rand.Next(itemCount);
                    int randomTagNo = rand.Next(itemCount / 5);
                    cmd.CommandText = "select * from ItemToTag where itemName like 'Item" + randomItemNo + "' and tagName like 'Tag" + randomTagNo + "'";
                    cmd.CommandType = CommandType.Text;
                    dr = cmd.ExecuteReader();
                    if (dr.Read())
                    {
                        //if the relation already exists, do not create it
                    }
                    else
                    {
                        cmd.CommandText = "insert into ItemToTag values ('Item" + randomItemNo + "','Tag" + randomTagNo + "')";
                        cmd.CommandType = CommandType.Text;
                        cmd.ExecuteNonQuery();
                    }

                }
                catch (Exception ex)
                {
                    Debug.WriteLine(ex.Message);
                }
            }
            Debug.WriteLine("ItemsToTag Generated");
            //generates the sources
            for (int i = 0; i < itemCount; i++)
            {
                //generates a random amount of sources for each item
                int recipeCount = rand.Next(3);
                int miningCount = rand.Next(3);
                int chestCount = rand.Next(3);
                int locationCount = rand.Next(3);
                int sourceCount = recipeCount + miningCount + chestCount + locationCount;
                //adds the total amount of required sources
                for (int a = 1; a <= sourceCount; a++)
                {
                    cmd.CommandText = "insert into ItemSource values ('Source" + a + "','Item" + i + "'," + rand.Next(100) + "," + rand.Next(5) + ")";
                    cmd.CommandType = CommandType.Text;
                    cmd.ExecuteNonQuery();
                }
                //adds individual sources to respective source tables
                int currentSource = 1;
                for (int a = 1; a < recipeCount; a++)
                {
                    cmd.CommandText = "insert into Recipe values ('Source" + a + "','Item" + i + "','Crafting')";
                    cmd.CommandType = CommandType.Text;
                    cmd.ExecuteNonQuery();
                    //adds random inputs to the recipes
                    for (int b = 1; b < rand.Next(3); b++)
                    {
                        int randomItemNo = rand.Next(itemCount);
                        cmd.CommandText = "select * from Input where inputItemName like 'Item" + randomItemNo + "' and outputItemName like 'Item" + i + "'";
                        cmd.CommandType = CommandType.Text;
                        dr = cmd.ExecuteReader();
                        if (dr.Read())
                        {
                            //if the relation already exists, do not create it
                        }
                        else
                        {
                            cmd.CommandText = "insert into Input values ('Source" + a + "','Item" + i + "','Item" + randomItemNo + "','Crafting')";
                            cmd.CommandType = CommandType.Text;
                            cmd.ExecuteNonQuery();
                        }
                    }
                }
                currentSource += recipeCount;
                for (int a = currentSource; a < currentSource + miningCount; a++)
                {
                    cmd.CommandText = "insert into Mining values ('Source" + a + "','Item" + i + "')";
                    cmd.CommandType = CommandType.Text;
                    cmd.ExecuteNonQuery();
                    //adds random inputs to blockToMine
                    for (int b = 1; b < rand.Next(3); b++)
                    {
                        int randomItemNo = rand.Next(itemCount);
                        cmd.CommandText = "select * from BlockToMine where inputItemName like 'Item" + randomItemNo + "' and outputItemName like 'Item" + i + "'";
                        cmd.CommandType = CommandType.Text;
                        dr = cmd.ExecuteReader();
                        if (dr.Read())
                        {
                            //if the relation already exists, do not create it
                        }
                        else
                        {
                            cmd.CommandText = "insert into BlockToMine values ('Source" + a + "','Item" + i + "','Item" + randomItemNo + "')";
                            cmd.CommandType = CommandType.Text;
                            cmd.ExecuteNonQuery();
                        }
                    }
                }
                currentSource += miningCount;
                for (int a = currentSource; a < currentSource + chestCount; a++)
                {
                    cmd.CommandText = "insert into Chest values ('Source" + a + "','Item" + i + "','Structure" + a + "')";
                    cmd.CommandType = CommandType.Text;
                    cmd.ExecuteNonQuery();
                }
                currentSource += chestCount;
                for (int a = currentSource; a < currentSource + locationCount; a++)
                {
                    cmd.CommandText = "insert into Location values ('Source" + a + "','Item" + i + "'," + rand.Next(-64, 256) + ",'Biome" + a + "')";
                    cmd.CommandType = CommandType.Text;
                    cmd.ExecuteNonQuery();
                }
                //Debug.WriteLine("Sour Generated");
            }
            Debug.WriteLine("Source Generated");
        }
    }
}



