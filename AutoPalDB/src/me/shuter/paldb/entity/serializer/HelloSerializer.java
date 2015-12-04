package me.shuter.paldb.entity.serializer;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

import me.shuter.paldb.entity.Hello;
import me.shuter.paldb.utils.WeightHelper;

import com.linkedin.paldb.api.Serializer;

@SuppressWarnings("serial")
public class HelloSerializer extends WeightHelper implements Serializer<Hello> {

	@Override
	public void write(DataOutput dataOutput, Hello input) throws IOException {
		dataOutput.writeInt(input.getId());
		dataOutput.writeInt(input.getScore());
		dataOutput.writeUTF(input.getText());
	}

	@Override
	public Hello read(DataInput dataInput) throws IOException {
		Hello output = new Hello();
		output.setId(dataInput.readInt());
		output.setScore(dataInput.readInt());
		output.setText(dataInput.readUTF());
		return output;
	}

	@Override
	public int getWeight(Hello instance) {
		int weight = 0;
		weight += getValueWeight(instance.getId());
		weight += getValueWeight(instance.getScore());
		weight += getValueWeight(instance.getText());
		return weight;
	}
}
