import cv2
import numpy as np
import pathlib
def process_image(imagePath):
    imagePath = pathlib.Path(imagePath)
    if not imagePath.exists() or not imagePath.is_file():
        print("Error opening image")
        exit(1)
    print(imagePath,imagePath.stem)
    image = cv2.imread(str(imagePath))
    if image is None:
        print("Error opening image")
        exit(1)
    imageH, imageW, _ = image.shape
    if imageW > 1536*2:
        print("warning: Image too wide",imagePath)
    halfW = imageW // 2
    white_image = np.full((imageH, imageW, 3), 255, dtype=np.uint8)
    image_mirror = cv2.flip(image, 1)

    image2 = white_image.copy()
    image2[0:imageH, 0:halfW] = image[0:imageH, halfW:imageW]
    image2[0:imageH, halfW:imageW] = image_mirror[0:imageH, 0:halfW]


    image4 = white_image.copy()
    image4[0:imageH, 0:halfW] = image_mirror[0:imageH, halfW:imageW]
    image4[0:imageH, halfW:imageW] = image[0:imageH, 0:halfW]
    
    outputPath = imagePath.parent/imagePath.stem
    outputPath.mkdir(parents=True,exist_ok=True)
    cv2.imwrite(str(outputPath/"1.jpg"), image)
    cv2.imwrite(str(outputPath/"2.jpg"), image2)
    cv2.imwrite(str(outputPath/"3.jpg"), image_mirror)
    cv2.imwrite(str(outputPath/"4.jpg"), image4)
    
    
for imagePath in pathlib.Path("images").glob("*.jpg"):
    process_image(imagePath)
process_image("images/dark-metallurgical-plant-workshop-industrial-interior_1332476-16433.png")
