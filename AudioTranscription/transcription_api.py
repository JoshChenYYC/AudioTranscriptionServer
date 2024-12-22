# Load PyTorch library, which loads machine learning models 
import torch  

# Loads the speed-to-text-model architecture
# Loads the processor that preprocesses audio data for model
# Controls the flow of data
from transformers import AutoModelForSpeechSeq2Seq, AutoProcessor, pipeline



def load_whisper():

    # Use GPU if possible, and adjusts float value to 16 bits if using GPU
    device = "cuda:0" if torch.cuda.is_available() else "cpu"
    torchDType = torch.float16 if torch.cuda.is_available() else torch.float32

    modelName = "openai/whisper-large-v3"


    


