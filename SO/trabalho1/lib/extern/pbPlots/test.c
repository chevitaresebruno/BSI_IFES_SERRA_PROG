#include "pbPlots.h"


int main()
{
    double x[] = {1, 2, 3, 4};
    double y[] = {1, 2, 3, 4};

    RGBABitmapImageReference* img = CreateRGBABitmapImageReference();
    StringReference *errorMessage = CreateStringReference(L"", 0);

    DrawScatterPlot(img, 600, 400, x, 4, y, 4, errorMessage);

    size_t lengh;
    ByteArray* pngData = ConvertToPNG(img->image);
    WriteToFile(pngData, "output.png");
    DeleteImage(img->image);
    FreeAllocations();

    return 0;
}