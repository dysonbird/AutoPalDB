package me.shuter.paldb.entity.serializer;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

import me.shuter.paldb.entity.Simple;
import me.shuter.paldb.utils.WeightHelper;

import com.linkedin.paldb.api.Serializer;

@SuppressWarnings("serial")
public class SimpleSerializer extends WeightHelper implements Serializer<Simple> {

	@Override
	public void write(DataOutput dataOutput, Simple input) throws IOException {
		dataOutput.writeInt(input.getId());
		dataOutput.writeUTF(input.getName());
		dataOutput.writeLong(input.getBigNumber());
	}

	@Override
	public Simple read(DataInput dataInput) throws IOException {
		Simple output = new Simple();
		output.setId(dataInput.readInt());
		output.setName(dataInput.readUTF());
		output.setBigNumber(dataInput.readLong());
		return output;
	}

	@Override
	public int getWeight(Simple instance) {
		int weight = 0;
		weight += getValueWeight(instance.getId());
		weight += getValueWeight(instance.getName());
		weight += getValueWeight(instance.getBigNumber());
		return weight;
	}
}
